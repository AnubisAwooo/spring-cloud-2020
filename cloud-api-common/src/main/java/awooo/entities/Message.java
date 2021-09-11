package awooo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message<T> {

    private Integer code;
    private String message;
    private T data;

    public Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
