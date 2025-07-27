import React from "react";
import './CountPeople.css'


class CountPeople extends React.Component{
    constructor(){
        super();
        this.state={
            entryCount: 0,
            exitCount: 0,
            c:0
        };
    }
    updateEntry(){
        this.setState((prevState, props)=>{
            return {entryCount: prevState.entryCount+1}
        });
    }
    updateExit(){
        this.setState((prevState, props)=>{
            return {exitCount: prevState.exitCount+1}
        });
    }
    render(){
        return(
            <div className="container">
                <div className="comp">
                    <button onClick={()=>this.updateEntry()}>Login</button>
                    <p>{this.state.entryCount} People Entered!!!</p>
                </div>
                <div className="comp">
                    <button onClick={()=>this.updateExit()}>Exit</button>
                    <p>{this.state.exitCount} People Left!!!</p>
                </div>
            </div>
        )
    }
}


export default CountPeople;