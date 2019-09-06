import axios from 'axios'

class TextService {
    executeUserService(name, city) {
        return axios.post('https://localhost:8443/api/v1/users', {
            Name: name,
            City: city
        });
    }

    executeTextService(text, userId) {
        return axios.post('https://localhost:8443/api/v1/texts', {
            Text: text,
            UserId: userId
        });
    }

    executeSubtextService(text, parentTextId) {
        return axios.post('https://localhost:8443/api/v1/texts/' + parentTextId + '/subtexts', {
            Text: text
        });
    }

}

export default new TextService();