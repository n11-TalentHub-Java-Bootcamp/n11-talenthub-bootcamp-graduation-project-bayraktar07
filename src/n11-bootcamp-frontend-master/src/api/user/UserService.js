import axios from "axios";

class UserService {


    getAllUsers() {

        const url = 'http://localhost:8080/api/v1/users';

        return axios.get(url);
    }

    getUser(userId) {
        var url = "http://localhost:8080/api/v1/users/" + userId;

        return axios.get(url);
    }

    saveUser(newUser){
        var url = "http://localhost:8080/api/v1/users";

        return axios.post(url, newUser);
    }

    deleteUser(userId) {

        var url = "http://localhost:8080/api/v1/users/$" + userId;

        return axios.delete(url);
    }

}
export default new UserService();