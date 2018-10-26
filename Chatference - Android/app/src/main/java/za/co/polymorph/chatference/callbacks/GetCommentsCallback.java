package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Comment;

public interface GetCommentsCallback {
    void commentsUpdate(Comment[] comments);
    void error(String message);
}
