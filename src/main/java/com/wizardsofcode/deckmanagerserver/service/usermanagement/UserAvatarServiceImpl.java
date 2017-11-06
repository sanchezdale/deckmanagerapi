/*
 * *                                      * *
 * * Created by: Daniel Alejandro Sanchez * *
 * * Date: 10/2/17                                 
 */

package com.wizardsofcode.deckmanagerserver.service.usermanagement;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.User;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserAvatar;
import com.wizardsofcode.deckmanagerserver.model.usermanagement.UserAvatarDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class UserAvatarServiceImpl implements UserAvatarService{


    private AmazonS3 amazonS3;

    private UserManagementService userManagementService;

    private UserAvatarDAO avatarDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserAvatarServiceImpl.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public UserAvatarServiceImpl(UserManagementService userManagementService, UserAvatarDAO avatarDAO, AmazonS3 s3){
        this.userManagementService = userManagementService;
        this.avatarDAO = avatarDAO;
        this.amazonS3 = s3;
    }


    private final String bucketName = "wizardsofthecode";

    private final String keyPrefix = "avatars/";

    private final String bucketUrl = "https://cdn.wizardsofcoding.com/";


    @Override
    public UserAvatar saveOrUpdateFile(MultipartFile file , User user) throws IOException{

        Calendar today = Calendar.getInstance();
        String filename = UUID.randomUUID().toString() + getImageType(file.getOriginalFilename());
        String fileKey = keyPrefix + filename;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getBytes().length);
        metadata.addUserMetadata("user", user.getUsername());
        metadata.addUserMetadata("uploaded", today.getTime().toString());

        AccessControlList accessControlList = new AccessControlList();
        accessControlList.grantPermission(GroupGrantee.AllUsers, Permission.Read);

        PutObjectRequest objectRequest = new PutObjectRequest(bucketName, fileKey, file.getInputStream(), metadata);
        objectRequest.withAccessControlList(accessControlList);

        PutObjectResult result = amazonS3.putObject(objectRequest);

        UserAvatar avatar = new UserAvatar();
        avatar.setAvatar(bucketUrl + filename);
        if(user.getAvatar() != null) {
            avatar = user.getAvatar();
            amazonS3.deleteObject(bucketName,avatar.getAvatar().substring(avatar.getAvatar().lastIndexOf("/")));
            avatar.setAvatar(bucketUrl + filename);
            avatarDAO.save(avatar);
        }
        else {
            avatar = avatarDAO.save(avatar);
            user.setAvatar(avatar);
            userManagementService.saveUser(user);
        }
        return avatar;
    }

    private String getImageType(String filename){

        if(filename.lastIndexOf(".") > 0){
            return filename.substring(filename.lastIndexOf("."));
        }else {
            return null;
        }
    }
}
