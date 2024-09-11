package social.media.network.services;

import social.media.network.entity.Comment.Comment;

public interface CommentServices {
    Comment postComment(Comment comment);

    Comment editComment(Comment comment);
}
