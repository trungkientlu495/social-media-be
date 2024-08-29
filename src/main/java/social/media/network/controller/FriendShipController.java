package social.media.network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.media.network.payload.ResponseHandler;
import social.media.network.payload.paging.Page;
import social.media.network.services.Impl.FriendShipServicesImpl;

@RestController
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
public class FriendShipController {
    private final FriendShipServicesImpl friendShipServices;
    private static Page page = new Page();
    private final static Pageable pageable = PageRequest.of(page.getPage(), page.getPageSize());
    @PostMapping("/send/request")
    public ResponseEntity<Object> sendRequestFriend(@RequestParam("idRecevived") Long idRecevived) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.sendRequestFriend(idRecevived));
    }

    @DeleteMapping("/delete/received-request")
    public ResponseEntity<Object> removeReceivedRequestFriend(@RequestParam("idUser") Long idUser) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.removeReceivedRequestFriend(idUser));
    }

    @DeleteMapping("/delete/sent-request")
    public ResponseEntity<Object> removeSentRequestFriend(@RequestParam("idUser") Long idUser) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.removeSentRequestFriend(idUser));
    }

    @PutMapping("/accept/request")
    public ResponseEntity<Object> acceptRequestFriend(@RequestParam("idUser") Long idUser) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.acceptRequestFriend(idUser));
    }

    @PutMapping("/unfriend")
    public ResponseEntity<Object> unFriendShip(@RequestParam("idUser") Long idUser) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.unFriendShip(idUser));
    }

//    @PutMapping("/set-relationship")
//    public ResponseEntity<Object> setRelationShip(@RequestParam("idUser") Long idUser) {
//        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,"1");
//    }

    // -> list user da nhan duoc loi moi ket ban cua user
    // -> tao mot rssponse SentRequest : firstname , lastname , avatar,time-> yêu cầu
    @GetMapping("/list")
    public ResponseEntity<Object> getAllFriends(@RequestParam("idUser") Long idUser) {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.listFriendShip(idUser,pageable).getContent());
    }

    @GetMapping("/list-sent-requests")
    public ResponseEntity<Object> getAllSentRequests() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.listSentRequestFriend(pageable).getContent());
    }

    @GetMapping("/list-received-requests")
    public ResponseEntity<Object> getAllReceivedRequests() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.listReceivedRequestFriend(pageable).getContent());
    }

    @GetMapping("/suggest-friend")
    public ResponseEntity<Object> getSuggestFriend() {
        return ResponseHandler.generateResponse(ResponseHandler.MESSAGE_SUCCESS, HttpStatus.OK,friendShipServices.listSuggestFrienda(pageable).getContent());
    }


}
