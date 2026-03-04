package com.siccase.vote_session_api.exception;

public class MemberAlreadyVoteException extends RuntimeException {
    public MemberAlreadyVoteException() {
        super("Member already vote, this topic just accepts one vote by member");
    }
}
