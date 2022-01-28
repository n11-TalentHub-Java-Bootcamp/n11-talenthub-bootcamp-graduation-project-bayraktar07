import React from "react";
import PageTitle from "../../gen/PageTitle";
import serialize from 'form-serialize';
import UserService from "../../../api/user/UserService";
import moment from "moment";

class AddUserPage extends React.Component {

    state = {
        User: {},
        success: "-"
    }

    handleFormSubmit = (e) => {

            e.preventDefault();
            const newUser = serialize(e.target, { hash: true })
            this.save(newUser);

    }

    save(newUser) {


        UserService.saveUser(newUser)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    handleResponse(response) {
        console.log(response.data);
        if (response.data.identificationNumber != null) {
            this.setState({ success: "SUCCESS - SAVED ID: " + response.data.identificationNumber });
        }

    }

    handleError(error) {
        console.log(error);
        this.setState({ success: "FAILED." + error.response.data.message });
    }

    clearForm() {
        document.getElementById("User-form").reset();
    }

    render() {
        return (

            <div className="container col-md-6 offset-md-3">

                <PageTitle title="Add User"></PageTitle>

                <form id="User-form" className="mt-5" onSubmit={this.handleFormSubmit}>

                    <div className="form-row">

                        <div className="form-group">
                            <label htmlFor="inputName">Name*</label>
                            <input type="text"
                                className="form-control"
                                pattern="[a-zA-Z]{2,}" title="Please enter your name correctly. Sembols or numbers are not supported. "
                                name="name" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">Surname*</label>
                            <input type="text"
                                className="form-control"
                                pattern="[a-zA-Z]{2,}" title="Please enter your surname correctly. Sembols or numbers are not supported. "
                                name="surname" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">User ID*</label>
                            <input type="text"
                                pattern="\d{11}" title="Please enter 11-digit length id"
                                className="form-control"

                                name="identificationNumber" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">Monthly Income*</label>
                            <input type="number"
                                defaultValue={0}
                                className="form-control"
                                name="monthlyIncome" />
                        </div>

                        <div className="form-group ">
                            <label htmlFor="inputName">Phone Number*</label>
                            <input
                                type="text"
                                className="form-control"
                                pattern="\d{10}" title="Please enter your phone number correctly. example: 5553332211 "
                                name="phoneNumber" />
                        </div>
                        <div className="form-group ">
                            <label htmlFor="inputName">Birth Date*</label>
                            <input type="date" name="birthDate"
                                max={moment().format("YYYY-MM-DD")}
                                className="form-control" />
                        </div>
                        <div className="form-group ">
                            <label htmlFor="inputRating">Deposit</label>
                            <input


                                type="number"
                                className="form-control "
                                name="deposit" />
                        </div>
                        <input type="submit" className="btn btn-danger btn-block" value="Save" />
                    </div>

                </form>
                <h6>Status: {this.state.success}</h6>
            </div>


        )
    }
}


export default AddUserPage;