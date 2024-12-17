package com.otorael.phone_dealer.ResponseDTO;

/**
 * A Data Transfer Object (DTO) that represents a simple message.
 * Used to send a message as part of the response.
 */
public class messageDTO {

    private String message;

    /**
     * Constructor to create a messageDTO with the provided message.
     *
     * @param message The message to be set for this DTO.
     */
    public messageDTO(String message) {
        this.message = message;
    }

    /**
     * Gets the message from the DTO.
     *
     * @return The message set in this DTO.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets a message in the DTO.
     *
     * @param message The message to set for this DTO.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
