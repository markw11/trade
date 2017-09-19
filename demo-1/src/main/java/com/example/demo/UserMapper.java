package com.example.demo;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
	@Select("select * from user where name = #{name}")
	public User findUserByName(@Param("name") String name);
	
	@Select("select * from user")
	public List<User> getAllUsers();

	@Options(useGeneratedKeys = true, keyProperty = "u.id")
	@Insert("INSERT INTO user(name) VALUES(#{u.name})")
	public void addUser(@Param("u") User user);

	@Delete("delete from user where id=#{id}")
	public void deleteUser(@Param("id") Long id);

	@Update("update user set name=#{name} where id=#{id}")
	public void updateUser(User user);
}
