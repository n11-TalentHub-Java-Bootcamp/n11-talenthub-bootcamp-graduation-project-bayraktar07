import React from "react";
import { Navbar, Container, Nav } from 'react-bootstrap/';

class Menu extends React.Component {

    render() {
        return <div className="col-md-6 offset-md-3 ">
            <Navbar bg="light" expand="lg">
                <Container>
                    <Navbar.Brand href="/">N11</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link href="/users/add">Add User</Nav.Link>
                            <Nav.Link href="/users/list">User List</Nav.Link>
                            <Nav.Link href="/applications/add">Credit Application</Nav.Link>
                            <Nav.Link href="/applications/find">Search Application</Nav.Link>

                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </div>


    }
}

export default Menu;