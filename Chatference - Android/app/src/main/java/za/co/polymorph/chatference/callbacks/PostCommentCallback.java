package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Comment;
import za.co.polymorph.chatference.model.Question;

public interface PostCommentCallback {
    void success(Comment comment);
    void error(String errorDescription);
}
