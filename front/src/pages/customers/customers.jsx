import React, {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import Modal from "../components/Modal/Modal";

const Customers = () => {

    const [customers, setCustomers] = useState([])
    const [customerInfo, setCustomerInfo] = useState([])
    const [getData, setGetData] = useState(false)

    const [modalActive, setModalActive] = useState(false)
    const [modalActiveCustomer, setModalActiveCustomer] = useState(false)

    const [name, setName] = useState('')
    const [money, setMoney] = useState(null)
    const [sex, setSex] = useState('')

    console.log('имя ' + name)
    console.log('деньги ' + money)
    console.log('секс ' + sex)

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    useEffect(() => {
        axios
            .get("http://localhost:8080/customers/show-all", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setCustomers(response.data)
                // console.log(response.data)
            });
    }, [getData]);


    const showCustomerInfo = useCallback((id) => {
        axios
            .get(`http://localhost:8080/purchases/${id}/get-purchases`, {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                if (response.status === 200) {
                    console.log(response.data)
                    setCustomerInfo(response.data)
                    setModalActive(true)
                } else {
                    console.log('Произошло некоторое дерьмо')
                }

            });
    }, [getData]);

    const deleteCustomer = async (id) => {
        await fetch(`http://localhost:8080/customers/delete/${id}`, {
            method: 'delete',
            headers: myHeaders,
            body: JSON.stringify(id)
        }).then((res) => {
            if (res.status === 200) {
                console.log('ok')
                setGetData((prevState => !prevState))
            }
            if (res.status === 403) {
                console.log('not Ok')
            }
            if (res.status === 500) {
                alert('товар уже был продан')
            }
        }).catch((errors) => console.error(errors))
    }


    const createCustomer = async (e) => {
        e.preventDefault();
        await fetch(`http://localhost:8080/customers/create?name=${name}&sex=${name}&money=${money}`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
        }).then((res) => {
            if (res.status === 400) {
                console.log('чёт не пошло')
            }
            if (res.status === 200) {
                console.log('всё ок')
                setGetData((prevState => !prevState))
            }
        }).catch((errors) => console.log(errors))
    }

    return (
        <div>
            <h1>Покупатели</h1>
            <div className='table'>
                <div className="table-wrapper">
                    <table className="fl-table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Имя</th>
                            <th>Пол</th>
                            <th>Деньги</th>
                            <th>Покупки</th>
                            <th>Удаление</th>
                        </tr>
                        </thead>
                        <tbody>
                        {customers.map(item =>
                            <>
                                <tr>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.sex}</td>
                                    <td>{item.money}</td>
                                    <td>
                                        {
                                            item.purchases.length !== 0
                                                ?
                                                <button onClick={() => showCustomerInfo(item.id)}>
                                                    Показать информацию
                                                </button>
                                                :
                                                <div>
                                                    Покупок нет
                                                </div>
                                        }
                                    </td>
                                    <td>
                                        <button onClick={() => deleteCustomer(item.id)}>
                                            Удалить пользователя
                                        </button>
                                    </td>
                                </tr>
                            </>
                        )}
                        </tbody>
                    </table>
                </div>
                <button onClick={() => setModalActiveCustomer(true)}>
                    Создать пользователя
                </button>
            </div>

            <Modal active={modalActiveCustomer} setActive={setModalActiveCustomer}>
                    <form action="" onSubmit={createCustomer}>
                        <div>
                            деньги пользователя <input type="text" onChange={event => setMoney(event.target.value)}
                                                       value={money}/>
                        </div>
                        <div>
                            имя пользователя <input type="text" onChange={event => setName(event.target.value)}
                                                    value={name}/>
                        </div>
                        <div>
                            пол пользователя <input type="text" onChange={event => setSex(event.target.value)}
                                                    value={sex}/>
                        </div>
                        <input type="submit" value="создать пользователя" className="type-1" onClick={() => {
                            setModalActiveCustomer(false)
                        }}/>
                    </form>
            </Modal>

            <Modal active={modalActive} setActive={setModalActive}>
                <form>
                    {
                        customerInfo !== null
                            ?
                            customerInfo.map(item => (
                                item.products.map(prod =>
                                    <>
                                        <div>{prod.name}</div>
                                        <div> Цена {prod.price}</div>
                                        <hr/>
                                    </>
                                )
                            )) : null
                    }
                </form>
            </Modal>

        </div>
    );
};

export default Customers;