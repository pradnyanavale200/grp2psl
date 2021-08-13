import React from 'react';

import {Navbar, Nav, Container, Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import book from '../books.png';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faHome,
    faUser,
    faSignOutAlt
  } from "@fortawesome/free-solid-svg-icons";

class NavigationBar extends React.Component{
    render(){
        return(
			<div>
                <Navbar bg="dark" variant="dark">
					<Container>
    				<Link to={"/manager/"} className='navbar-brand'>
                    <img
                        src={book}
                        width="35"
                        height="35"
                        alt="brand"
                    />{" "}
                        Learning Manager
                    </Link>
                    <Nav>
                        <Link to={"/manager/"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-success"
                            >
                            <FontAwesomeIcon icon={faHome} />
                            </Button>{" "}</Link>
                        <Link to={"/manager/"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-primary"
                            >
                            <FontAwesomeIcon icon={faUser} />
                            </Button>{" "}</Link>
                        <Link to={"/manager/"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-danger"
                            >
                            <FontAwesomeIcon icon={faSignOutAlt} />
                            </Button>{" "}</Link>
                    </Nav>
                    </Container>                
                </Navbar>
            </div>);
    }
}

export default NavigationBar;