package jupgo.jupgoserver.domain.user;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.user.SaveUserRequestDto;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String kakaoId;

    @OneToMany(mappedBy = "user")
    private List<Tree> trees;

    public User(SaveUserRequestDto saveUserRequestDto) {
        this.nickname = saveUserRequestDto.getNickname();
        this.email = saveUserRequestDto.getEmail();
        this.kakaoId = saveUserRequestDto.getKakaoId();
    }

    public User() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public List<Tree> getTrees() {
        return trees;
    }
}
