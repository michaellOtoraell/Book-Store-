package com.otorael.phone_dealer.ResponseDTO;

/**
 * A Data Transfer Object (DTO) that holds user information.
 * Used to return user details such as message, name, email, and token as part of the response.
 */
public class UserInfoDTO {

    private String message;
    private String firstName;
    private String lastName;
    private String email;
    private String token;

    /**
     * Constructor to create a UserInfoDTO with the provided details.
     *
     * @param message    The message to include in the response.
     * @param firstName  The first name of the user.
     * @param lastName   The last name of the user.
     * @param email      The email of the user.
     * @param token      The authentication token for the user.
     */
    public UserInfoDTO(String message, String firstName, String lastName, String email, String token) {
        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
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

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The first name to set for the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The last name to set for the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the authentication token of the user.
     *
     * @return The token assigned to the user.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token for the user.
     *
     * @param token The token to set for the user.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
