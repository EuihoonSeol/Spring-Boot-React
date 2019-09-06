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
            userNameInput : '',
            textInput : '',
            doneResult : false,
        }
        this.createUserAndText = this.createUserAndText.bind(this)
        this.handleSuccessfulUserResponse = this.handleSuccessfulUserResponse.bind(this)
        this.handleSuccessfulTextResponse = this.handleSuccessfulTextResponse.bind(this)
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
                    <div>
                        <h4>Results :</h4>
                        <div>User Name = {this.state.userName}</div>
                        <div>
                            <h5>Texts :</h5>
                            {this.state.parentTextContent} {this.state.parentTextDateentered}
                        </div>
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
        })
    }

    handleSuccessfulTextResponse(response) {
        this.setState({
            parentTextId : response.data.id,
            parentTextContent : response.data.content,
            parentTextDateentered : response.data.dateTime,
            doneResult : true,
        })
    }
}

export default TextComponent;