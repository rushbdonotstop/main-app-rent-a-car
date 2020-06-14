package com.example.review.controller;

import com.example.review.model.Notification;
import com.example.review.model.Review;
import com.example.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /**
     * GET /server/review
     *
     * @return return all reviews
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getAll() throws Exception {
        List<Review> reviews = reviewService.getAll();
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

    /**
     * GET /server/review/{reviewId}
     *
     * @return return review by id
     */
    @GetMapping(value = "/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> get(@PathVariable Long reviewId) throws Exception {
        Review review = reviewService.get(reviewId);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    /**
     * DELETE /server/review/{reviewId}
     *
     * @return return status of delete review action
     */
    @DeleteMapping(value = "/{reviewId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long reviewId) throws Exception {
        Notification notification = reviewService.delete(reviewId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/review
     *
     * @return return status of create review
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody Review review) throws Exception {
        Notification notification = reviewService.create(review);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * UPDATE /server/review
     *
     * @return return status of update review
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> update(@RequestBody Review review) throws Exception {
        Notification notification = reviewService.update(review);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * GET /server/review/{vehicleId}
     *
     * @return return review by vehicleId
     */
    @GetMapping(value = "/getByVehicleId/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getByVehicleId(@PathVariable Long vehicleId) throws Exception {
        List<Review> reviews = reviewService.getByVehicleId(vehicleId);
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

    /**
     * GET /server/review/getPending
     *
     * @return return pending reviews
     */
    @GetMapping(value = "/getByVehicleId/getPending", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Review>> getPending() throws Exception {
        List<Review> reviews = reviewService.getPending();
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }
}
