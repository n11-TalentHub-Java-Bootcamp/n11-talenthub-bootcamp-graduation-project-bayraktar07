import React from "react";
import PageTitle from "../../gen/PageTitle";
import serialize from 'form-serialize';

class AddUserPage extends React.Component {

    state = {
        User: {}
    }

    handleFormSubmit = (e) => {
        e.preventDefault();
        const newUser = serialize(e.target, { hash: true })

        this.save(newUser);
       
    }

    save(newUser) {
        const requestOptions = {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        }

        fetch('http://localhost:8080/api/v1/users', requestOptions)
            .then(response => response.json())
            .then(data => { 
                console.log("data sent")
                console.log(data)
                this.setState({ User: data })
                
                if(data.id ){
                    this.clearForm(); 
                }
                
            })
            ;

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
                            <label htmlFor="inputName">Name</label>
                            <input type="text"
                                className="form-control"
                                
                                name="name" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">Surname</label>
                            <input type="text"
                                className="form-control"
                                
                                name="surname" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">User ID</label>
                            <input type="text"
                                className="form-control"
                                
                                name="identificationNumber" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputName">Monthly Income</label>
                            <input type="number"
                                className="form-control"
                                
                                name="monthlyIncome" />
                        </div>

                        <div className="form-group ">
                            <label htmlFor="inputName">Phone Number</label>
                            <input
                                type="text"
                                className="form-control"
                                
                                name="phoneNumber" />
                        </div>
                        <div className="form-group ">
                        <label htmlFor="inputName">Birth Date</label>
                        <input type="date" name="birthDate"
                            className="form-control"/>
                        </div>
                        <div className="form-group ">
                            <label htmlFor="inputRating">Deposit</label>
                            <input
                                className="form-control "
                               
                                name="deposit" />
                        </div>
                    </div>

                    <input type="submit" className="btn btn-danger btn-block" value="Save" />
                </form>
            </div>
        )
    }
}

export default AddUserPage;
