import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

class ShowTrainers extends React.Component{
    constructor(props){
        super(props);
        this.state={
            trainers: [],
            msg:""
        };
    }
    
    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/trainers/");
			if(response.data != null) {
				this.setState({
					trainers: response.data
				});	
			}	
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
    }

    componentDidMount(){
        this.showData();
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Trainers</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Trainer Id</th>
                                <th>Trainer Name</th>
                                <th>Department</th>
                                <th>Phone Number</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.trainers.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Trainers Available.</td>
                                </tr>
                                ) : (
                                this.state.trainers.map((trainer) => (
                                    <tr key={trainer.trainerid}>
                                    <td>{trainer.trainerid}</td>
                                    <td>{trainer.name}</td>
                                    <td>{trainer.department}</td>
                                    <td>{trainer.phonenumber}</td>
                                    <td>{trainer.email}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                        >
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Button>{" "}
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
                                        >
                                            <FontAwesomeIcon icon={faTrash} />
                                        </Button>
                                        </ButtonGroup>
                                    </td>
                                    </tr>
                                ))
                                )}

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ShowTrainers;