package com.packtpub.mmj.booking.domain.service;

import com.packtpub.mmj.booking.domain.message.BookingMessageChannels;
import com.packtpub.mmj.booking.domain.model.entity.Booking;
import com.packtpub.mmj.booking.domain.model.entity.Entity;
import com.packtpub.mmj.booking.domain.repository.BookingRepository;
import com.packtpub.mmj.booking.domain.valueobject.BookingVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


@Service("bookingService")
@EnableBinding(BookingMessageChannels.class)
public class BookingServiceImpl extends BaseService<Booking, String> implements BookingService {

    private static final Logger LOG = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository<Booking, String> bookingRepository;
    private BookingMessageChannels bookingMessageChannels;

    @Autowired
    public void setBookingMessageChannels(BookingMessageChannels bookingMessageChannels) {
        this.bookingMessageChannels = bookingMessageChannels;
    }

    @Autowired
    public BookingServiceImpl(BookingRepository<Booking, String> bookingRepository) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void add(Booking booking) throws Exception {
        if (bookingRepository.containsName(booking.getName())) {
            throw new Exception(String.format("There is already a product with the name - %s", booking.getName()));
        }

        if (booking.getName() == null || "".equals(booking.getName())) {
            throw new Exception("Booking name cannot be null or empty string.");
        }
        super.add(booking);
        produceBookingOrderEvent(booking);
    }

    @Override
    public Collection<Booking> findByName(String name) throws Exception {
        return bookingRepository.findByName(name);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.update(booking);
    }

    @Override
    public void delete(String id) {
        bookingRepository.remove(id);
    }

    @Override
    public Entity<String> findById(String id) {
        return bookingRepository.get(id);
    }

    @Override
    public void produceBookingOrderEvent(Booking booking) {
        /*BookingOrder.Builder boBuilder = BookingOrder.newBuilder();
        boBuilder.setId(booking.getId());
        boBuilder.setName(booking.getName());
        boBuilder.setRestaurantId(booking.getRestaurantId());
        boBuilder.setTableId(booking.getTableId());
        boBuilder.setUserId(booking.getUserId());
        boBuilder.setDate(booking.getDate().toString());
        boBuilder.setTime(booking.getTime().toString());
        BookingOrder bo = boBuilder.build();*/
        BookingVO bo = new BookingVO(booking.getId(), booking.getName(), booking.getRestaurantId(), booking.getTableId(),
                booking.getUserId(), booking.getDate(), booking.getTime());

        Message<BookingVO> message = MessageBuilder.withPayload(bo).build();
        bookingMessageChannels.bookingOrderOutput().send(message);
        LOG.info("sending bookingOrder: {}", booking);
    }

    @Override
    public Collection<Booking> findByCriteria(Map<String, ArrayList<String>> name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
