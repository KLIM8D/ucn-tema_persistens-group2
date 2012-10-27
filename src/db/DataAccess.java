package db;

/**
 * Created: 12-10-2012
 * @version: 0.1
 * Filename: DataAccess.java
 * Description:
 * @changes
 */

import com.sun.rowset.CachedRowSetImpl;
import javax.sql.rowset.CachedRowSet;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DataAccess
{
    private PreparedStatement _sqlCommand;
    private int _dbType = 1;
    private final String _connectionString = "jdbc:sqlserver://IP-ADDR:1433;" +
                                             "databaseName=DBNAME;" +
                                             "user=USERNAME;" +
                                             "password=PASSWORD";
    private final boolean _enableSqlMonitor = true;
    private static Connection _con;
    private static DataAccess _instance;

    public PreparedStatement getSqlCommandText()
    { return _sqlCommand; }
    public void setSqlCommandText(PreparedStatement value)
    { _sqlCommand = value; }

    public void setDbType(int value)
    { _dbType = value; }

    public Connection getCon() throws SQLException
    {
        if(_con == null || isClosed())
            openConnection();
        return _con;
    }

    public static DataAccess getInstance()
    {
        return _instance != null ? _instance : (_instance = new DataAccess());
    }

    private DataAccess()
    {
        switch(_dbType)
        {
            case 1:
                try
                {
                    //MSSQL Server
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;

        }
    }

    /**
     * Open the connection to the database
     *
     */
    public void openConnection()
    {
        try
        {
            _con = DriverManager.getConnection(_connectionString);
            _con.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("ConnectionString: " + _connectionString);
        }
    }

    /**
     * Close the connection to the database
     *
     */
    public static void closeConnection()
    {
        try
        {
            _con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Set AutoCommit to false. Which will hold back any transactions/queries
     *
     */
    public static void startTransaction()
    {
        try
        {
            _con.setAutoCommit(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Set AutoCommit to true. Which will execute the queries immediately
     *
     */
    public static void commitTransaction()
    {
        try
        {
            _con.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
    * Rollback the failed update you performed
    *
    */
    public static void rollbackTransaction()
    {
        try
        {
            _con.rollback();
            _con.setAutoCommit(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get a ResultSet which contains the rows returned from the database. comment
     *
     * @return ResultSet the rows returned from the database
     *
     */
    public ResultSet callCommandGetResultSet()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            ResultSet newResultSet = _sqlCommand.executeQuery();
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            if(isClosed())
                openConnection();

            cachedRowSet.populate(newResultSet);
            newResultSet.close();
            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
            _sqlCommand = null;
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "CallCommandGetResultSet()");
        }

        return null;
    }

    /**
    * Get a ResultSet which contains the rows returned from the database. comment
    * The method doesn't contain benchmark/query execution time.
    *
    * @return ResultSet the rows returned from the database
    *
    */
    private ResultSet callCommandGetResultSetWithOutMonitor()
    {
        try
        {
            ResultSet newResultSet = _sqlCommand.executeQuery();
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            if(isClosed())
                openConnection();

            cachedRowSet.populate(newResultSet);
            newResultSet.close();
            return cachedRowSet;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
            _sqlCommand = null;
        }

        return null;
    }

    /**
    * Used for insert, update and delete
    *
    * @return int number of rows affected by the query
    *
    */
    public int callCommand()
    {
        int returnVal = 0;

        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            if(isClosed())
                openConnection();
            returnVal = _sqlCommand.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (_con != null)
                closeConnection();
            _sqlCommand = null;
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommand()");
        }

        return returnVal;
    }

    /**
    * Get a single value from a column
    *
    * @return String the value from the database
    *
    */
    public String callCommandGetField()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        String returnVal = "";

        try
        {
            ResultSet listData = callCommandGetResultSetWithOutMonitor();
            if (listData != null && listData.next())
            {
                returnVal = listData.getString(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(_con != null)
                closeConnection();
            _sqlCommand = null;
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime-startTime), "callCommandGetField()");
        }

        return returnVal;
    }

    /**
    * Get a single row from the database
    *
    * @return ResultSet which contains the row
    *
    */
    public ResultSet callCommandGetRow()
    {
        //Benchmark time start
        ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
        long startTime;
        long finishTime;

        if (_enableSqlMonitor)
            startTime =  threadmxbean.getCurrentThreadCpuTime();

        try
        {
            ResultSet listData = callCommandGetResultSetWithOutMonitor();
            CachedRowSet dataSet = new CachedRowSetImpl();
            if (listData != null)
            {
                dataSet.populate(listData);
                listData.close();
                return dataSet;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(_con != null)
                closeConnection();
            _sqlCommand = null;
        }

        if (_enableSqlMonitor)
        {
            finishTime = threadmxbean.getCurrentThreadCpuTime();
            sqlMonitor((finishTime - startTime), "callCommandGetField()");
        }

        return null;
    }

    /**
    * Format a Date object to SQL datetime format
    *
    * @param d the date which you want formated
    * @return returns a formated String
    *
    */
    public String dateToSqlDate(java.util.Date d)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(d);
    }

    /**
    * Get SQL current date and time
    *
    * @param sqlDate the sql.Date object which you want time extracted from
    * @return Returns java.util.date
    *
    */
    public java.util.Date sqlDateToDate(java.sql.Date sqlDate)
    {
        return new Date(sqlDate.getTime());
    }

    private void sqlMonitor(long sqlExecutionTime, String dalFunction)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Running method:" + dalFunction +  System.getProperty("line.separator"));
        sb.append("Execution time: " + sqlExecutionTime + " ns" +  System.getProperty("line.separator"));
        System.out.println(sb.toString());
    }

    private boolean isClosed()
    {
        Statement stmt;
        ResultSet rs;
        try
        {
            stmt = _con.createStatement();
            rs = stmt.executeQuery("SELECT 1");
            if (rs.next())
                return false; // connection is valid
        }
        catch (SQLException ex)
        {
            //ex.printStackTrace();
            return true;
        }

        return false;
    }
}
