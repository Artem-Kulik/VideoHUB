package com.example.springboot.loader;

import com.example.springboot.constants.Roles;
import com.example.springboot.models.Channel;
import com.example.springboot.models.Role;
import com.example.springboot.models.User;
import com.example.springboot.models.Video;
import com.example.springboot.repositories.ChannelRepository;
import com.example.springboot.repositories.RoleRepository;
import com.example.springboot.repositories.UserRepository;
import com.example.springboot.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final VideoRepository videoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(RoleRepository roleRepository,
                          VideoRepository videoRepository,
                          ChannelRepository channelRepository,
                          UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.videoRepository=videoRepository;
        this.channelRepository=channelRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {

        if(this.roleRepository.count()==0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if(this.userRepository.count()==0)
        {
//            this.userRepository.save(
//                    new User("semen@gmail.com",passwordEncoder.encode("123456"), "blabla")
//            );
        }
        if(this.channelRepository.count()==0)
        {
            User user=this.userRepository.findByName("vlad");
            this.channelRepository.save(
                    new Channel("animeMad","def.png",user)
            );
        }
        if(this.videoRepository.count()==0)
        {
            this.videoRepository.save(
                    new Video("1.mp4","Евангелион 1 серия 1 сезон","Рус. озвучка , видео взято с сайта jut.su","prev.jpeg",this.channelRepository.findById(1).get())
            );
            this.videoRepository.save(
                    new Video("2.mp4","Джоджо 1 серия 2 сезон","Рус. озвучка , видео взято с сайта jut.su","prev.jpeg",this.channelRepository.findById(1).get())
            );
        }
    }
}