import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import MyToast from './MyToast';
import axios from 'axios';
import {DATABASE_URL, TRAINER_URL} from '../constants';

export default class UpdateTrainerPassword extends Component{
	constructor(props){
		super(props);
		this.state = {currentPassword:'',newpassword:'',confirmpassword:''};
		this.sendPassword = this.sendPassword.bind(this);
		this.passwordChange = this.passwordChange.bind(this);
		this.goBack = this.goBack.bind(this);
	}
	
	async sendPassword(event){
		event.preventDefault();
		
		try{ 
			console.log(this.state.newpassword)
			console.log(this.state.currentPassword)
			console.log(localStorage.getItem("username"))
			console.log(localStorage.getItem("password"))
			if(this.state.newpassword===this.state.confirmpassword){
				const response = await axios.put(DATABASE_URL+TRAINER_URL+"/updatetrainer/" + localStorage.getItem("userId")+"/currentPassword/"+this.state.currentPassword+"/newPassword/"+this.state.newpassword, 
			{},{auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
                }});
                console.log(response)
				if(response != null){
					alert("Password updated successfully");
					localStorage.setItem("password", this.state.newpassword);
				}
				}
			else{
				alert("Check confirm password");
			}	
				}
		 catch(error) {
			alert(error.response);
		}
			
			
	};
	
	passwordChange(event){
		this.setState({
			[event.target.name]:event.target.value
		})
	}
	
	goBack(){
    	this.props.history.goBack();
	}
	
	initialState = {
		password:''
	};
	
	render(){
		return (<Card className={"border border-dark bg-dark text-white mt-5"}>
			<Card.Header>
				Change Password
			</Card.Header>
			<Form id = "credentialsform" onSubmit = {this.sendPassword}>
			
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>Current Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter current password" 
					    onChange = {this.passwordChange}
					    value = {this.state.currentPassword}
					    name = "currentPassword"/>
					  </Form.Group>
				</Card.Body>
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>New Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter new password" 
					    onChange = {this.passwordChange}
					    value = {this.state.newpassword}
					    name = "newpassword"/>
					  </Form.Group>
				</Card.Body>
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>Confirm Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Reenter password" 
					    onChange = {this.passwordChange}
					    value = {this.state.confirmpassword}
					    name = "confirmpassword"/>
					  </Form.Group>
				</Card.Body>
				
				
			<Card.Footer style = {{"textAlign":"right"}}>
				<Button size = "sm" variant="success" type="submit"> Submit </Button>{" "}				
				<Button size = "sm" variant="info" type="button" onClick = {this.goBack}> Go Back </Button>								
			</Card.Footer>
			</Form>
		</Card>);
	}
}