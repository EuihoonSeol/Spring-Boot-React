import axios from 'axios'

class TextService {
    executeTextService(text) {
        return axios.post('http://localhost:8443/api/v1/texts', {
            Text: text
        });
    }
}

export default new TextService();