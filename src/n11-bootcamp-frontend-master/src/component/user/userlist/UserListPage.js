import React from "react";
import UserService from "../../../api/user/UserService";
import PageTitle from "../../gen/PageTitle";

class UserListPage extends React.Component {


    state = {

        userList: []
    }

    componentDidMount() {
        this.getUserList();
    }

    getUserList() {

        UserService.getAllUsers()
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    handleResponse(response) {
        this.setState({ userList: response.data })
    }

    handleError(error) {
        console.log(error)
    }


    render() {
    
            return (
                <div className="row col-md-6 offset-md-3">
    
                    <PageTitle title="Tüm Uyeler"></PageTitle>
    
                    {this.state.userList.map((user, i) => (
    
                        <div className="col-lg-4" key={i}>
                            <div className="card mb-4 shadow-sm">
                                <div className="card-body text-center">
                                    <h6 className="card-title ">Name: {user.name}</h6>
                                    <h6 className="card-title ">Surname: {user.surname}</h6>
                                    <h6 className="card-title ">ID: {user.identificationNumber}</h6>
                                    <h6 className="card-title ">Birth Date: {user.birthDate}</h6>
                                    <h6 className="card-title ">Phone: {user.phoneNumber}</h6>
                                    <h6 className="card-title ">Income: {user.monthlyIncome}</h6>
                                    <h6 className="card-title ">Score: {user.creditScore}</h6>
                                    <h6 className="card-title ">Deposit: {user.deposit}</h6>

                                </div>
                            </div>
                        </div>
    
                    ))}
                </div>
            )
    }
}
export default UserListPage;