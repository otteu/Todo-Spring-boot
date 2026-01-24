package com.example.todo.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.todo.domain.Member;
import com.example.todo.domain.MemberRole;

import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@BeforeEach
	void setUp()
	{
		for (int i = 0; i<10; i++)
		{
			Member member = Member.builder()
					.email("user"+i+"@google.com")
					.pw(passwordEncoder.encode("1111"))
					.nickname("USER"+i)
					.build();
			member.addRole(MemberRole.USER);
			
			if(i >= 5)
				member.addRole(MemberRole.MANAGER);
			
			if(i >=8)
				member.addRole(MemberRole.ADMIN);
			
			memberRepository.save(member);
		}	
	}
	
	@Disabled
	@Test
	public void testInsertMember()
	{
		
		for (int i = 0; i<10; i++)
		{
			Member member = Member.builder()
					.email("user"+i+"@google.com")
					.pw(passwordEncoder.encode("1111"))
					.nickname("USER"+i)
					.build();
			member.addRole(MemberRole.USER);
			
			if(i >= 5)
				member.addRole(MemberRole.MANAGER);
			
			if(i >=8)
				member.addRole(MemberRole.ADMIN);
			
			memberRepository.save(member);
		}	
	}
	
	@Test
	public void testRead()
	{
		String email = "user9@google.com";
		
		Member member = memberRepository.getWithRoles(email);
		
		log.info("-----------------------------------");
		log.info(member);
		log.info(member.getMemberRoleList());
	}
	
	
}
