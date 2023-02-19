package jupgo.jupgoserver.dto.user;

import lombok.Data;

@Data
public class SaveUserRequestDto {
    private Long id;
    private String nickname;

    private String email;
    private String kakaoId;

    private String code;

    public String getKakaoId() {
        return kakaoId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
