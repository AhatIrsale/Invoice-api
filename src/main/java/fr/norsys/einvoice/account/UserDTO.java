package fr.norsys.einvoice.account;

import java.util.List;

public record UserDTO(String firstName, String lastName, List<String> roles){}

