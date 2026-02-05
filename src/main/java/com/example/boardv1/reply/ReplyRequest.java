package com.example.boardv1.reply;

import lombok.Data;

public class ReplyRequest {
    
    @Data
    public static class ReplySaveDTO{
        private Integer boardId;
        private String comment;
    }
}
