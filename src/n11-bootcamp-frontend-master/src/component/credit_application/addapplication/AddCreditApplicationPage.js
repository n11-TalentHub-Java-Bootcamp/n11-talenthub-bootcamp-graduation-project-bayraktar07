import React from "react";
import ApplicationService from '../../../api/application/ApplicationService';
import PageTitle from "../../gen/PageTitle";
import serialize from 'form-serialize';

class AddCreditApplicationPage extends React.Component {

    state = {
        Application: {},
        appliedInfo: {},
        success: "-"
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        const newApplication = serialize(e.target, { hash: true })
        this.save(newApplication);
        this.clearForm();
    }

    save(identificationNumber) {

        ApplicationService.saveApplication(identificationNumber.identificationNumber)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    handleResponse(response) {
        console.log(response);
        this.setState({ appliedInfo: response.data })
        this.setState({ success: "Applied for ID: " + response.data.userId })
    }

    handleError(error) {
        console.log(error.response.data);
        this.setState({success: error.response.data.message})
    }

    clearForm() {
        document.getElementById("Application-form").reset();
    }

    render() {

        var applied = this.state.appliedInfo;

        console.log(applied);
        return (
            <div className="container col-md-6 offset-md-3">

                <PageTitle title="Application"></PageTitle>

                <form id="Application-form" className="mt-5" onSubmit={this.handleFormSubmit}>

                    <div className="form-row">
                        <div className="form-group">
                            <label htmlFor="inputName">User ID</label>
                            <input type="text"
                                pattern="\d{11}" title="Please enter 11-digit length id"
                                className="form-control"

                                name="identificationNumber" />
                        </div>
                    </div>
                    <input type="submit" className="btn btn-danger btn-block" value="Apply" />
                </form>

                <div className="card-body text-center">
                <h6 className="card-title ">Status: {this.state.success}</h6>
                    <h6 className="card-title ">Credit Result: {applied.creditResult}</h6>
                    <h6 className="card-title ">Application Date: {applied.applicationDate}</h6>
                    <h6 className="card-title ">Limit: {applied.creditLimit}</h6>
                    <h6 className="card-title ">User ID: {applied.userId}</h6>
                </div>
            </div>
        )
    }
} export default AddCreditApplicationPage;