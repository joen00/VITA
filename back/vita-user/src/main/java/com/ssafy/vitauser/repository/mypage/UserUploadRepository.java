package com.ssafy.vitauser.repository.mypage;

import com.ssafy.vitauser.entity.user.User;
import com.ssafy.vitauser.entity.user.UserHistory;
import com.ssafy.vitauser.entity.user.UserUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserUploadRepository extends JpaRepository<UserUpload, Integer> {

    UserUpload findByUser(User user);

}
