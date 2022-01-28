import axios from "axios";

class ApplicationService {


    saveApplication(identificationNumber){
        var url = "http://localhost:8080/api/v1/applications/?identificationNumber=" + identificationNumber;

        return axios.post(url, identificationNumber);
    }

    findApplication(identificationNumber, birthDate) {
        var url= "http://localhost:8080/api/v1/applications/identification-numbers/" + identificationNumber + "?birthDate=" + birthDate;

        return axios.get(url);
    }

}
export default new ApplicationService();