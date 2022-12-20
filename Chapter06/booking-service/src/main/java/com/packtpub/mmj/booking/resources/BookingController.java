package com.packtpub.mmj.booking.resources;

import com.packtpub.mmj.booking.domain.model.entity.Booking;
import com.packtpub.mmj.booking.domain.model.entity.Entity;
import com.packtpub.mmj.booking.domain.service.BookingService;
import com.packtpub.mmj.booking.domain.valueobject.BookingVO;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class.getName());

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Booking>> findByName(@RequestParam("name") String name) {
        logger.info("booking-service findByName() invoked:{} for {} ", bookingService.getClass().getName(), name);
        Collection<Booking> bookings;
        try {
            bookings = bookingService.findByName(name.trim().toLowerCase());
        } catch (Exception ex) {
            logger.error("Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return bookings.size() > 0
                ? new ResponseEntity<>(bookings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Entity<String>> findById(@PathVariable("id") String id) {
        logger.info("booking-service findById() invoked:{} for {} ", bookingService.getClass().getName(), id);
        Entity<String> booking;
        try {
            booking = bookingService.findById(id.trim());
        } catch (Exception ex) {
            logger.warn("Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return booking != null
                ? new ResponseEntity<>(booking, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Booking> add(@RequestBody BookingVO bookingVO) {
        logger.info("booking-service add() invoked: {} for {}", bookingService.getClass().getName(), bookingVO.getName());
        System.out.println(bookingVO);
        Booking booking = new Booking(null, null, null, null, null, null, null);
        BeanUtils.copyProperties(bookingVO, booking);
        try {
            bookingService.add(booking);
        } catch (Exception ex) {
            logger.warn("Exception raised add Booking REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
