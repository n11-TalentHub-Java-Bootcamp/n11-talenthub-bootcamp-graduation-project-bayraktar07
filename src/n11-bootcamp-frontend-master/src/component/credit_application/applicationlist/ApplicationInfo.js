import React from "react";
import ApplicationService from "../../../api/application/ApplicationService";
import serialize from 'form-serialize';

class ApplicationInfo extends React.Component {



    state = {
        appliedInfo: {}
    }

    componentDidMount() {
        this.getApplication();
    }

    getApplication(identificationNumber, birthDate) {

        ApplicationService.findApplication(identificationNumber, birthDate)
            .then(response => this.handleResponse(response))
            .catch(error => this.handleError(error))
            ;
    }

    handleResponse(response) {
        this.setState({ appliedInfo: response.data });
    }

    handleError(error) {
        console.log(error.response.data);
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        const application = serialize(e.target, { hash: true })
        this.getApplication(application.identificationNumber, application.birthDate);
        this.clearForm();
    }

    render() {

        var applied = this.state.appliedInfo;
        return (

            <div>
                <form id="Application-form" className="mt-5" onSubmit={this.handleFormSubmit}>

                    <div className="form-row">
                        <div className="form-group">
                            <label htmlFor="inputName">User ID</label>
                            <input type="text"
                                pattern="\d{11}" title="Please enter 11-digit length id"
                                className="form-control"

                                name="identificationNumber" />
                        </div>
                        <div className="form-group ">
                            <label htmlFor="inputName">Birth Date*</label>
                            <input type="date" name="birthDate"
                                className="form-control" />
                        </div>
                    </div>
                    <input type="submit" className="btn btn-danger btn-block" value="Search" />
                </form>

                <div className="card-body text-center">
                    <h6 className="card-title ">Credit Result: {applied.creditResult}</h6>
                    <h6 className="card-title ">Application Date: {applied.applicationDate}</h6>
                    <h6 className="card-title ">Credit Limit: {applied.creditLimit}</h6>
                    <h6 className="card-title ">User ID: {applied.userId}</h6>
                </div>
            </div>

        )
    }
}

export default ApplicationInfo;
