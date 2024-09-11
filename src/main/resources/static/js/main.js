'use strict'

var join_group = document.querySelector('#join-group');
var commentInput = document.querySelector(  '#commentInput');
var commentSection = document.getElementById('comment-parent');
join_group.addEventListener('submit', connect, true);
var stompClient = null;
var username = "Trinh Ngoc Quyên";



function connect(event) {
    if(username) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);

    }
    event.preventDefault();

}

function onError(error) {
    console.error('Error connecting to WebSocket:', error);
}


function onConnected() {
    var commenta = {
        username: username,
        comment: commentInput.value,
        createdAt: new Date()
    }
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/comment', onMessageReceivedComment);

    // Tell your username to the server
    stompClient.send("/app/comment/post",
        {},JSON.stringify(commenta)
    )

}


// danh comment cho post
function onMessageReceivedComment(payload) {
    var abc = JSON.parse(payload.body);

    commentSection.innerHTML += `
    <div id="comment">
        <img src="user-avatar.jpg" alt="Avatar" class="avatar">
        <div class="comment-content">
            <span class="username">Trinh Ngọc Quyên</span>
            <p class="comment-text">${abc.comment}</p>
        </div>
    </div>
`;
    commentInput.value= "";
    disConnect();

}

function disConnect() {
    stompClient.disconnect(function() {
    });
}






