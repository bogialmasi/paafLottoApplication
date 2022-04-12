package spring.database.config.db;

import org.springframework.jdbc.core.RowMapper;
import spring.database.model.impl.LottoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class LottoRowMapperImpl implements RowMapper<Lotto> {
    private LottoType toLottoType(Integer type){
        return LottoType.fromInteger(type);
    }

    private Set<Integer> toSet(String numbers){
        HashSet<Integer> result = new HashSet<>();
        System.out.println(numbers);
        return result;
    }

    @Override
    public Lotto mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Lotto(
                rs.getInt("year"),
                rs.getInt("week"),
                toLottoType(rs.getInt("type")),
                toSet(rs.getString("numbers")));
    }
}
