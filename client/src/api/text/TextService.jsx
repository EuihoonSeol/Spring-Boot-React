import axios from 'axios'

class TextService {
    executeUserService(name) {
        return axios.post('http://localhost:8443/api/v1/users', {
            Name: name
        });
    }

    executeTextService(text, userId) {
        return axios.post('http://localhost:8443/api/v1/texts', {
            Text: text,
            UserId: userId
        });
    }
}

export default new TextService();