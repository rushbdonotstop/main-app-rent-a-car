import { Component, OnInit, ViewChild, Inject, NgZone } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { MAT_DIALOG_DATA } from '@angular/material';
import { MapsAPILoader } from '@agm/core';

class MapDTO {
  id: number
}

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
  firstCenter: number = null;
  marker: any

  private serverUrl = 'http://localhost:8081/vehicle/socket'
  private title = 'WebSockets chat';
  private stompClient;
  vehicleId: any;


  constructor(private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    console.log(data.id)
    this.vehicleId = data.id;
    console.log(this.vehicleId)
    this.initializeWebSocketConnection(this.vehicleId,this.firstCenter)
  }

  ngOnInit() {

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
    
  }


  initializeWebSocketConnection(vehicleId,firstCenter) {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/chat", (message) => {
        if (message.body) {
          var coordsAndId = []
          coordsAndId = message.body.split(',')
          var id = coordsAndId[2]
          console.log(id)
          console.log(vehicleId)
          if (id == vehicleId) {
            console.log('recieved coords')
            if (firstCenter == null) {
              firstCenter = { lat: Number(coordsAndId[0]), lng: Number(coordsAndId[1].substr(1)) }
              this.map = new google.maps.Map(document.getElementById('map'), {
                center: firstCenter,
                zoom: 8
              });
              var image = "assets/img/car.png"
              this.marker = new google.maps.Marker({
                position: { lat: Number(coordsAndId[0]), lng: Number(coordsAndId[1].substr(1)) },
                map: this.map,
                title: 'Live vehicle feed',
                icon: image
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
