import React, {Component} from 'react'
import TextService from '../../api/text/TextService'

class TextComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            userId : '',
            userName : '',
            parentTextId : '',
            parentTextContent : '',
            parentTextDateentered : '',
            subTexts : [],
            userNameInput : '',
            textInput : '',
            responseInput : '',
            doneResult : false,
            respondingResult : false,
        }
        this.createUserAndText = this.createUserAndText.bind(this)
        this.handleSuccessfulUserResponse = this.handleSuccessfulUserResponse.bind(this)
        this.handleSuccessfulTextResponse = this.handleSuccessfulTextResponse.bind(this)
        this.createResponse = this.createResponse.bind(this)
        this.handleSuccessfulRespondingResponse = this.handleSuccessfulRespondingResponse.bind(this)
    }

    render() {
        return (
            <div>
                <form onSubmit={(e) => {this.createUserAndText(); e.preventDefault();}}>
                    User Name :
                    <input type="text" name="userName" value = {this.state.userNameInput} onChange={ e => this.setState({userNameInput: e.target.value})} required />
                    <br/>
                    Text :
                    <input type="text" name="text" value = {this.state.textInput} onChange={ e => this.setState({textInput: e.target.value})} required />
                    <br/>
                    <input type="submit" value="Done" />
                </form>
                {this.state.doneResult ?
                    <div className="doneResult">
                        <h4>Results :</h4>
                        <div>User Name = {this.state.userName}</div>
                        <br/>
                        <div>
                            <div>Texts = </div>
                            {this.state.parentTextContent} {this.state.parentTextDateentered}
                            <form onSubmit={(e) => {this.createResponse(); e.preventDefault();}}>
                                <input type="text" name="response" value = {this.state.responseInput} onChange={ e => this.setState({responseInput: e.target.value})} required />
                                <input type="submit" value="Responding" />
                            </form>
                        </div>
                    </div> :
                    ""
                }
                { this.state.respondingResult ?
                    <div className="respondingResult">
                        {this.state.subTexts.map((subtext) => {
                            return <ul key={subtext}>{subtext}</ul>
                        })}
                    </div> :
                    ""
                }
            </div>
        );
    }

    createUserAndText() {
        TextService
            .executeUserService(this.state.userNameInput)
            .then( response => {
                this.handleSuccessfulUserResponse(response);
                TextService.executeTextService(this.state.textInput, this.state.userId)
                    .then( response => this.handleSuccessfulTextResponse(response) )
                    .catch( error => console.log(error) )
            })
            .catch( error => console.log(error) );
    }

    handleSuccessfulUserResponse(response) {
        this.setState({
            userId : response.data.id,
            userName : response.data.name,
            doneResult : false,
            respondingResult : false,
        })
    }

    handleSuccessfulTextResponse(response) {
        this.setState({
            parentTextId : response.data.id,
            parentTextContent : response.data.content,
            parentTextDateentered : response.data.dateTime,
            doneResult : true,
            respondingResult : false,
        })
    }

    createResponse() {
        TextService
            .executeSubtextService(this.state.responseInput, this.state.parentTextId)
            .then( response => this.handleSuccessfulRespondingResponse(response) )
            .catch( error => console.log(error) );
    }

    handleSuccessfulRespondingResponse(response) {
        let tempArr = [];
        for (let i = 0; i < response.data.length; i++) {
            let tempObj = "";
            tempObj = response.data[i].content + " " + response.data[i].dateTime;
            tempArr.push(tempObj);
        }

        this.setState({
            subTexts : tempArr,
            respondingResult : true,
        })
    }

}

export default TextComponent;