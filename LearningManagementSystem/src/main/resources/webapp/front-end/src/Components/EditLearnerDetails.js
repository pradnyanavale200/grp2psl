import React from 'react';

import {Card, Form, Button, Container, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo
  } from "@fortawesome/free-solid-svg-icons";

class EditLearnerDetails extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.register = this.register.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        id: this.props.location.state.learner.learnerId,
        name: this.props.location.state.learner.name,
        department: this.props.location.state.learner.department,
        phonenumber: this.props.location.state.learner.phoneNumber,
        email: this.props.location.state.learner.email,
        msg:""
      };

    resetForm = () => {
        this.setState(() => this.initialState);
    };
    
    validateForm(phoneNumber) {
        if(phoneNumber.length > 11 || phoneNumber.length < 10){
            alert("Enter valid phoneNumber");
            return false;
        }
        return true;
    }

    async register(event){
		event.preventDefault();
		const learner = {
            learnerId: this.state.id,
            name: this.state.name,
            department: this.state.department,
            phoneNumber: this.state.phonenumber,
            email: this.state.email
        }
        if(this.validateForm(learner.phoneNumber) === true){
            this.setState({
                msg:"Processing..\nPlease Wait"
            });
            try{
                const response = await axios.put("http://localhost:8080/LearningManagementSystem/learners/update", learner);
                if(response.data != null){
                    alert("Learner updated successfully");
                    console.log(response.data);
                }	
            } catch(error) {
                alert(error);
            }
        }
    }

    formChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render(){
        return(
			<div className="mt-5">
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Register Learner</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Form onSubmit={this.register} onReset={this.resetForm} id="registerId" >
                <Card.Body>
                    <Container>
                        <Row>
                            <Col>
                            <Form.Group className="mb-3" controlId="name">
                                <Form.Label>Name</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="test" 
                                    value={this.state.name}
                                    disabled={true}
                                    name="name"
                                    placeholder="Enter name" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="department">
                                <Form.Label>Department</Form.Label>
                                <Form.Control 
                                    type="text" autoComplete="off" 
                                    value={this.state.department}
                                    onChange={this.formChange}
                                    name="department"
                                    placeholder="Enter department" />
                            </Form.Group>
                            </Col>
                            <Col>
                            <Form.Group className="mb-3" controlId="phoneNumber">
                                <Form.Label>Phone Number</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="text" 
                                    value={this.state.phonenumber}
                                    onChange={this.formChange}
                                    name="phonenumber"
                                    placeholder="Enter phone number" />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="email">
                                <Form.Label>Email</Form.Label>
                                <Form.Control required autoComplete="off"
                                    type="email" 
                                    value={this.state.email}
                                    disabled={true}
                                    name="email"
                                    placeholder="Enter email" />
                            </Form.Group>
                            </Col>
                        </Row>
                        
                    </Container>
                        
                </Card.Body>
                <Card.Footer style={{"text-align":"right"}}>
                    <Button variant="success" type="submit">
                        <FontAwesomeIcon icon={faSave} />{" "}
                        Submit
                    </Button>{" "}
                    <Button variant="danger" type="reset">
                        <FontAwesomeIcon icon={faUndo} />{" "}
                        Reset
                    </Button>
                </Card.Footer>
                </Form>
            </Card>
      </div>
        );
    }
}

export default EditLearnerDetails;