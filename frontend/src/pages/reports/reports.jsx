import React, {useEffect, useState} from 'react';
import axios from "axios";

const Reports = () => {

    const [bestCustomer, setBestCustomer] = useState([])
    const [allMoney, setAllMoney] = useState(null)
    const [getData, setGetData] = useState(false)
    const [spendedMoney,setSpendedMoney] = useState(null)
    const [customers, setCustomers] = useState([])
    const [bigMoney,setBigMoney] = useState(null)

    useEffect(() => {
        axios
            .get("http://localhost:8080/report/must-spending-customer", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setBestCustomer(response.data)
                console.log(response.data)
            });
    }, [getData]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/report/remaining-money", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setAllMoney(response.data)
                console.log(response.data)
            });
    }, [getData]);

    useEffect(() => {
        axios
            .get(" http://localhost:8080/report/spended-money", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setSpendedMoney(response.data)
                console.log(response.data)
            });
    }, [getData]);

    useEffect(() => {
        axios
            .get("http://localhost:8080/report/show-grouped-customers", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setCustomers(response.data)
                console.log(response.data)
            });
    }, [getData]);

    useEffect(() => {
        axios
            .get("http://localhost:8080//report/must-spending-customer-spent-money", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setBigMoney(response.data)
                console.log(response.data)
            });
    }, [getData]);






        return (
        <div>
            <h1>Отчёты</h1>
            <h1>Лучший покупатель</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Имя</th>
                            <th>Пол</th>
                            <th>Всего денег потрачено</th>
                        </tr>
                        </thead>
                        <tbody>
                            <>
                                <tr>
                                    <td>{bestCustomer.id}</td>
                                    <td>{bestCustomer.name}</td>
                                    <td>{bestCustomer.sex}</td>
                                    <td>{bigMoney}</td>
                                </tr>
                            </>
                        </tbody>
                    </table>
                </div>
            </div>
            <h1>Деньги</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>Денег осталось</th>
                            <th>Денег потрачено</th>
                        </tr>
                        </thead>
                        <tbody>
                        <>
                            <tr>
                                <td>{allMoney}</td>
                                <td>{spendedMoney}</td>
                            </tr>
                        </>
                        </tbody>
                    </table>
                </div>
            </div>
            <h1>Покупатели сгрупированные по полу</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Имя</th>
                            <th>Пол</th>
                            <th>Деньги</th>
                        </tr>
                        </thead>
                        <tbody>
                        {customers.map(customer =>
                        <>
                            <tr>
                                <td>{customer.id}</td>
                                <td>{customer.name}</td>
                                <td>{customer.sex}</td>
                                <td>{customer.money}</td>
                            </tr>
                        </>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
};

export default Reports;