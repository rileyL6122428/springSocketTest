import { Observer } from 'rxjs/Observer';
import { Observable } from 'rxjs/Observable';
import { Nameable } from '../../domain/base/nameable';

export class Store<T extends Nameable> {

    protected records: Map<string, T>;
    protected observer: Observer<Store<T>>;
    protected observable: Observable<Store<T>>;

    constructor() {
        this.records = new Map<string, T>();
        this.observable = new Observable<Store<T>>((observer) => {
            this.observer = observer;
        });

    }

    get updates(): Observable<Store<T>> {
        return this.observable;
    }

    depositList(records: Array<T>): void {
        records.forEach((record) => this.insertRecord(record));
        this.emitUpdate();
    }

    deposit(record: T): void {
        this.insertRecord(record);
        this.emitUpdate();
    }

    private insertRecord(record: T): void {
        this.records.set(record.name, record);
    }

    private emitUpdate(): void {
        this.observer.next(this);
    }

    recordsAsList(): Array<T> {
        const recordsList = new Array<T>();
        this.records.forEach((record: T, name: string) => {
            recordsList.push(record);
        });
        return recordsList;
    }

}
