package it.develhope.websocket.Services.Web.Socket2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientMessageDTO {

    private String clientName;
    private String clientAlert;
    private String clientMsg;

}
