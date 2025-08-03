import { Component } from 'react';

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      fullName: '',
      email: '',
      password: ''
    };
  }

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleSubmit = (e) => {
    e.preventDefault();
    const { fullName, email, password } = this.state;

    if (fullName.length < 5) {
      alert("Full Name must be 5 characters long!");
    } else if (!email.includes('@') || !email.includes('.')) {
      alert("Email is not valid!");
    } else if (password.length < 8) {
      alert("Password must be 8 characters long!");
    } else {
      alert("Form Submitted Successfully");
    }
  };

  render() {
    return (
      <div>
        <h2 style={{ color: 'red'}}>Register Here!!!</h2>
        <form onSubmit={this.handleSubmit}>
          <div>
            <label>Name: </label>
            <input
              type="text"
              name="fullName"
              value={this.state.fullName}
              onChange={this.handleChange}
              required
            />
          </div>
          <br />
          <div>
            <label>Email: </label>
            <input
              type="text"
              name="email"
              value={this.state.email}
              onChange={this.handleChange}
              required
            />
          </div>
          <br />
          <div>
            <label>Password: </label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleChange}
              required
            />
          </div>
          <br />
          <button type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default Register;
