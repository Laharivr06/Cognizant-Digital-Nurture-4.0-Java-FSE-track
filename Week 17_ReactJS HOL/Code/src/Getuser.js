import { Component } from 'react';

class Getuser extends Component {
  constructor() {
    super();
    this.state = {
      person: null,
      loading: true,
    };
  }

  async componentDidMount() {
    const url = "https://api.randomuser.me/";
    const response = await fetch(url);
    const data = await response.json();
    this.setState({ person: data.results[0], loading: false });
    console.log(data.results[0]);
  }

  render() {
    if (this.state.loading || !this.state.person) {
      return <h2>Loading...</h2>;
    }

    return (
      <div style={{marginTop: '50px' }}>
        <h3>{this.state.person.name.title} {this.state.person.name.first} {this.state.person.name.last}</h3>
        <img src={this.state.person.picture.large}/>
      </div>
    );
  }
}

export default Getuser;
