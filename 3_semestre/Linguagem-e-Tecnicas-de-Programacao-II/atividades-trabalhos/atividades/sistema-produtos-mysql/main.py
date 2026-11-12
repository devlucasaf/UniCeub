from conexao import create_connection
from banco import create_database, create_table
from menu import menu


def main():
    connection = create_connection()

    if not connection:
        return

    database_name = "sistema_produtos"

    create_database(connection, database_name)
    create_table(connection)

    menu(connection)

    if connection.is_connected():
        connection.close()
        print("Conexão ao MySQL encerrada")


if __name__ == "__main__":
    main()
