import { Component, OnInit, ViewChild } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';


@Component({
  selector: 'pm-map-dialog',
  templateUrl: './map-dialog.component.html',
  styleUrls: ['./map-dialog.component.css']
})
export class MapDialogComponent implements OnInit {

  @ViewChild('gmap', { static: true }) gmapElement: any;
  map: google.maps.Map;

  geoCoder: google.maps.Geocoder;
  latitude: number;
  longitude: number;
  zoom: number;
  address: any;

  loadMap = false;
  firstCenter: number
  marker: any

  private serverUrl = 'http://localhost:8081/vehicle/socket'
  private title = 'WebSockets chat';
  private stompClient;

  private vehicleId: number;

  constructor(public data: number) { }

  ngOnInit() {

    this.vehicleId = this.data

    this.map = new google.maps.Map(document.getElementById('map'), {
      center: { lat: -34.397, lng: 150.644 },
      zoom: 8
    });
    var image = "assets/img/car.png"
    var marker = new google.maps.Marker({
      position: { lat: -34.397, lng: 150.644 },
      map: this.map,
      title: 'Live vehicle feed',
      icon: image
    });
    this.initializeWebSocketConnection()
  }


  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/chat", (message) => {
        if (message.body) {
          var coordsAndId = []
          coordsAndId = message.body.split(',')
          var id = coordsAndId[2]
          if (id == this.vehicleId) {
            console.log('recieved coords')
            if (this.firstCenter == null) {
              this.firstCenter = { lat: Number(coordsAndId[0]), lng: Number(coordsAndId[1].substr(1)) }
              this.map = new google.maps.Map(document.getElementById('map'), {
                center: this.firstCenter,
                zoom: 8
              });
            }
            else {
              //deletes the last marker
              this.marker.setMap(null);
              var image = "assets/img/car.png"
              this.marker = new google.maps.Marker({
                position: { lat: Number(coordsAndId[0]), lng: Number(coordsAndId[1].substr(1)) },
                map: this.map,
                title: 'Live vehicle feed',
                icon: image
              });
            }
          }
        }
      });
    });
  }

  sendMessage(message) {
    this.stompClient.send("/app/send/message", {}, message);
  }


}
