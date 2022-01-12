import React, {useEffect, useState} from 'react';
import axios from "axios";
import Modal from "../components/Modal/Modal";
import "../components/Modal/modal.css"

const Products = () => {

    const [products, setProducts] = useState([])
    const [getData, setGetData] = useState(false)

    const [modalActive, setModalActive] = useState(false)

    const [nameProduct, setNameProduct] = useState('')
    const [price, setPrice] = useState('')

    const myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');

    useEffect(() => {
        axios
            .get("http://localhost:8080/products/show-all", {
                headers: {
                    "Access-Control-Allow-Origin": `*`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify,
                responseType: "json",
            })
            .then((response) => {
                setProducts(response.data)
                console.log(response.data)
            });
    }, [getData]);

    const deletePost = async (id) => {
        await fetch(`http://localhost:8080/products/delete/${id}`, {
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
        }).catch((errors) => console.error(errors))
    }

    const nameHandler = (e) => {
        setNameProduct(e.target.value)
        console.log(nameProduct)
    }


    return (
        <div>
            {products.map(item =>
                <div>
                    {item.name}
                    <button onClick={() => deletePost(item.id)}>
                        удалить позицию
                    </button>
                </div>
            )}
            <h1>Продукты</h1>
            <button onClick={() => setModalActive(true)}>
                добавить позицию
            </button>

            <Modal active={modalActive} setActive={setModalActive}>
                <div>
                    имя товара <input type="text"/>
                </div>
                <div>
                    цена товара <input type="text" onChange={e=>nameHandler(e)} value={nameProduct}/>
                </div>
                <input type="submit" value="создать товар" className="type-1" onClick={() => {
                    setModalActive(false)
                }}/>
            </Modal>
        </div>
    );
};

export default Products;