using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using Test_Exam.Models;
using MySql.Data.MySqlClient;

namespace Test_Exam.DataAbstractionLayer
{
    public class DAL
    {
        public List<Student> GetStudentsFromGroup(int group_id)
        {
            MySql.Data.MySqlClient.MySqlConnection conn;
            string myConnectionString;

            myConnectionString = "server=localhost;uid=root;pwd=;database=wp;";
            List<Student> slist = new List<Student>();

            try
            {
                conn = new MySql.Data.MySqlClient.MySqlConnection();
                conn.ConnectionString = myConnectionString;
                conn.Open();

                MySqlCommand cmd = new MySqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from students where group_id=" + group_id;
                MySqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Student stud = new Student();
                    stud.Id = myreader.GetInt32("id");
                    stud.Nume = myreader.GetString("name");
                    stud.Password = myreader.GetString("password");
                    stud.Group_id = myreader.GetInt32("group_id");
                    slist.Add(stud);
                }
                myreader.Close();
            }
            catch (MySql.Data.MySqlClient.MySqlException ex)
            {
                Console.Write(ex.Message);
            }
            return slist;
        }
    }
}