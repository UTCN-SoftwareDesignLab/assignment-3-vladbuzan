import * as SockJS from 'sockjs-client';
import { encodeParams } from '../api/http';

const useMessaging = () => {
    
    const getClient = () => {
        var Stomp = require('stompjs');
        Stomp.over(SockJS("http://localhost:8088/gs-guide-websocket"))
        return new Stomp.over(SockJS("http://localhost:8088/gs-guide-websocket"))
    }

    const subscribe = (url, onMessageReceived, client) => {
        client.connect({}, () => {
            client.subscribe(url, onMessageReceived)
        })
    }

    const sendMessage = (url, data, client) => {
        client.send(url, {}, JSON.stringify(encodeParams(data)))

    }

    return [getClient(), subscribe, sendMessage]
}

export default useMessaging