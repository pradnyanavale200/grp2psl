import './App.css';

import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import NavigationBar from './Components/NavigationBar';
import Welcome from './Components/Welcome';
import EnrollLearner from './Components/EnrollLearner';
import Register from './Components/RegisterTrainer';
import ShowTrainers from './Components/ShowTrainers';
import ViewCourses from './Components/ViewCourses';
import CourseRegister from './Components/CourseRegister';
import RegisterLearner from './Components/RegisterLearner';
import ShowLearners from './Components/ShowLearners';
import { Col, Container, Row } from 'react-bootstrap';

function App() {
  return (
    <div className="App">
    <Router>
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12}>
          <Switch>
              <Route path="/" exact component={Welcome}/>
              <Route path="/enroll" exact component={EnrollLearner}/>
              <Route path="/register" exact component={Register}/>
              <Route path="/show" exact component={ShowTrainers}/>
              <Route path="/viewCourse" exact component={ViewCourses}/>
              <Route path="/registerCourse" exact component={CourseRegister}/>
              <Route path="/registerLearner" exact component={RegisterLearner}/>
              <Route path="/showLearners" exact component={ShowLearners}/>
          </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    </div>
  );
}

export default App;
