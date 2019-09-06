import React, {Component} from 'react'
import TextService from '../../api/text/TextService'

class TextComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            text : '',
            textInput : '',
        }
        this.createText = this.createText.bind(this)
        this.handleSuccessfulTextResponse = this.handleSuccessfulTextResponse.bind(this)
    }

    render() {
        return (
            <div>
                <form onSubmit={(e) => {this.createText(); e.preventDefault();}}>
                    <input type="text" value = {this.state.textInput} name="text" onChange={ e => this.setState({textInput: e.target.value})} required/>
                    <input type="submit" value="Done" />
                </form>
                {this.state.text}
            </div>
        );
    }

    createText() {
        TextService
            .executeTextService(this.state.textInput)
            .then( response => {
                this.handleSuccessfulTextResponse(response)
            })
            .catch ( error => console.log(error) );
    }

    handleSuccessfulTextResponse(response) {
        this.setState({
            text : response.data.content,
            textInput : '',
        })
    }
}

export default TextComponent;