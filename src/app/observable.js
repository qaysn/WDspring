class NewsPaper {
    constructor(name) {
        this.name = name;
    }
    publishNews(news) {
        console.log(this.name + ' : ' + news);
    }
}

class FashionNewsObservable {
    constructor() {
        this.subscribers = [];
    }
    subscribe(sub) {
        this.subscribers.push(sub);
    }
    unsubscribe(exSub) {
        this.subscribers = this.subscribers.filter(sub => sub !== exSub);
    }

    notify(news) {
        this.subscribers.forEach(newsPaper => newsPaper.publishNews(news));
    }
}

const oriflame = new NewsPaper('oriflame');
const avon = new NewsPaper('avon');
const glamour = new NewsPaper('glamour');

const milano = new FashionNewsObservable();

milano.subscribe(oriflame);
milano.subscribe(avon);
milano.subscribe(glamour);


milano.notify('Hello World');
milano.unsubscribe(oriflame);
milano.notify('Without Oriflame(');