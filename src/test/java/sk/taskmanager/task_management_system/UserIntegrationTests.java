package sk.taskmanager.task_management_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import sk.taskmanager.task_management_system.api.exception.BadRequestException;
import sk.taskmanager.task_management_system.api.exception.ResourceNotFoundException;
import sk.taskmanager.task_management_system.api.request.UserAddRequest;
import sk.taskmanager.task_management_system.domain.User;

import java.util.List;

public class UserIntegrationTests extends IntegrationTest{
    @Test
    public void  getAll(){
       final ResponseEntity<List<User>> usersResponse = testRestTemplate.exchange(
                "/user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        Assertions.assertEquals(HttpStatus.OK, usersResponse.getStatusCode());
        Assertions.assertNotNull(usersResponse.getBody());
        Assertions.assertTrue(usersResponse.getBody().size()>=2);
    }
    @Test
    public void insert(){
insertUser(generateRandomUser());
    }

    @Test
    public void getUser(){
        final UserAddRequest request = generateRandomUser();
        final long id = insertUser(request);
       final ResponseEntity<User> userResponse = testRestTemplate.getForEntity(
                "/user/" +id,
                User.class

        );
       Assertions.assertEquals(HttpStatus.OK, userResponse.getStatusCode());
       Assertions.assertNotNull(userResponse.getBody());
       final User user = userResponse.getBody();
       Assertions.assertEquals(id, user.getId());
       Assertions.assertEquals(request.getName(), user.getName());
        Assertions.assertEquals(request.getEmail(), user.getEmail());
    }

    @Test
    public void deleteUser(){
        final UserAddRequest request = generateRandomUser();
        final long id = insertUser(request);

        final ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(
                "/user/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        Assertions.assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        final ResponseEntity<ResourceNotFoundException> getResponse =  testRestTemplate.getForEntity(
                "/user/" + id,
                ResourceNotFoundException.class
        );
        Assertions.assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());

    }

    @Test
    public void insertEmailAlreadyExists(){
        final UserAddRequest request = generateRandomUser();
        final long id = insertUser(request);
       final ResponseEntity<BadRequestException> badRequest = testRestTemplate.postForEntity(
                "/user",
                request,
                BadRequestException.class
        );
       Assertions.assertEquals(HttpStatus.BAD_REQUEST, badRequest.getStatusCode());
    }

    private UserAddRequest generateRandomUser(){
        return new UserAddRequest(
                "name" + Math.random(),
                "email" + Math.random()
        );
    }


    private long insertUser(UserAddRequest request){
   final ResponseEntity<Long>  insertResponse = testRestTemplate.postForEntity(
        "/user",
        request,
        Long.class
);
Assertions.assertEquals(HttpStatus.CREATED, insertResponse.getStatusCode());
Assertions.assertNotNull(insertResponse.getBody());
 return  insertResponse.getBody();
    }
}
